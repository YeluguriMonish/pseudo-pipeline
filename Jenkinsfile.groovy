node {
    stage('Preparation') {

        def targets = [[name: "john", age: 23, deployTo: "gaia"], [name: "tim", age: 42, deployTo: "gaia"], [name: "bob", age: 13, deployTo: "gaia"]]

        def blueTargets = []
        def greenTargets = []
        String step = "blue"
        retry = true
        retryCount = 0

        targets.each { target ->
          if (target.deployTo == 'gaia') {
            blueTargets.add(target)
            greenTargets.add([target,[:]])
          }
        }

        while (retry) {
          if (!blueTargets.isEmpty()) {
            env = [:]
            target = blueTargets.pop()
            step = "blue"
          } else if (!greenTargets.isEmpty()) {
            target = greenTargets.pop()
            step = "green"
          } else {
            break
          }

          echo "current target type: $step current target: $target"
          echo "blue targets: $blueTargets"
          echo "green targets: $greenTargets"

          try {
            if (step == 'blue') {
              env['age'] = target.age
              greenTargets.each { greenTarget ->
                if (target.name == greenTarget[0].name) {
                  greenTarget[1].putAll(env)
                }
              }
            } else if (step == 'green') {
              a = target[0]
              b = target[1]
              force error
            }
          } catch (Exception e) {
            echo "$e"
            if ( step == 'blue') {
              blueTargets.push(target)
            } else if (step == 'green') {
              echo "in green catch block"
              blueTargets.push(target[0])
              greenTargets.push(target)
            }
            retryCount++
            if (retryCount > 3) {
              retry = false
              echo "retry count exceeded, exiting loop"
            }
          }
        }

    }
}
