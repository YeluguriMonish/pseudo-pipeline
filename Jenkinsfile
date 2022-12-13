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

          try {
            if (step == 'blue') {
              env['age'] = target.age
              greenTargets.each { greenTarget ->
                if (target.name == greenTarget[0].name) {
                  greenTarget[1].putAll(env)
                }
              }
            } else if (step == 'green') {
              echo "$target[0]"
              echo "$target[1]"
            }
          } catch (Exception e) {
            echo "$e"
            if ( step == 'blue') {
              blueTargets.push(target)
            } else if (step == 'green') {
              blueTargets.push(target)
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
