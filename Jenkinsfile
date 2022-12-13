node {
    stage('Preparation') {

        def targets = [[name: "john", age: 23, deployTo: "gaia"], [name: "tim", age: 42, deployTo: "gaia"], [name: "bob", age: 13, deployTo: "gaia"]]

        def blueTargets = []
        def greenTargets = []
        String step = "blue"

        targets.each { target ->
          if (target.deployTo == 'gaia') {
            blueTargets.add(target)
            greenTargets.add(target)
          }
        }

        while true {
          if blueTargets (!blueTargets.isEmpty()) {
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
              echo "blue deployment"
            } else if (step == 'green') {
              echo "green deployment"
            }
          } catch (Exception e) {
            if ( step == 'blue') {
              blueTargets.push(target)
            } else if (step == 'green') {
              blueTargets.push(target)
              greenTargets.push(target)
            }
          }
        }

    }
}
