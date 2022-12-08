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

        echo "$blueTargets"
        echo "$greenTargets"
    }
}
