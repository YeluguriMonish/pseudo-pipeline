node {
    stage('Preparation') {

        def targets = [[name: "john", age: 23, deployTo: "gaia"], [name: "tim", age: 42, deployTo: "gaia"], [name: "bob", age: 13, deployTo: "gaia"]]

        def blueTargets = []
        def greenTargets = []

        targets.each { target ->
          if (target.deployTo == 'gaia') {
            blueTargets.add(target)
            greenTargets.add(target)
          }
        }

        String deployStep = "blue"
        boolean retryFullDeploy = true

        while (retryFullDeploy) {
          if (!blueTargets.isEmpty()) {
            target = blueTargets.pop()
            deployStep = "blue"
            env = []
          } else if (!greenTargets.isEmpty()) {
              target = greenTargets.pop()
              env = target[1]
              target = target[0]
          } else {
              break
          }

          if (deployStep == "blue") {
            echo "blue"
            echo "target.name"
            env.add("age=${target.age}")
            for (greenTarget in greenTargets) {
              if (target.name == greenTarget[0].name) {
                greenTarget[1].add(env)
              }
            }
          }

          if (deployStep == "green") {
            withEnv(env) {
              echo $target.name
              echo $env.age
            }
          }
        }
}
}
