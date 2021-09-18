# KK_9

This Project is designed to be a mobile application for android devices that allows a user to bet on real sports games against AI driven bots.

## Built on Android Studios & Springboot

### Project Structure
The folders outlined in this repository will be organized as follows:
* KK_9/Backend 		- (Springboot Architecture)
* KK_9/Frontend		- (Android Studio Architecture)
* KK_9/Documents	- (Any design or non-code based files)
* KK_9/Experiments	- (Architectures under test)

#### WorkFlow - GitLab
* Pull the latest code from the master branch whenever you would like the latest stable release on your computer.
```
git checkout master
git pull https://git.linux.iastate.edu/cs309/fall2020/kk_9.git
```
* Immediately create a branch for the feature you would like to add, or the bug you would like to fix, and switch to 
that branch before you start editing any code. (Note: You can view all the branchees on your local pc with the 'git 
branch' command)
```
git checkout -b name-of-feature-or-bug-fix-branch
```
* Once done adding features you must add that file that you changed so you are able to push it to the master. You can also add a message to describe what you did.
```
git add file-name
git commit -m "insert message"
```
* Once you beleive your feature release or bug fix is stable, create a merge request into the master so that we can 
review and choose to incorporate the code.
```
git checkout master
git push origin branch-name
```
* Go to GitLab UI and change from master to new branch to check changes. Then request a merge and describe what you did.

#### Accessing Server and Editing Yml File
* make sure you are connected to iowa state's network
* it takes 2-3 minutes when connecting so be patient
* open command line and enter command below with your own netid:
```
ssh YOURNETID@coms-309-kk-09.cs.iastate.edu
```
* enter the command below in the server to access the root directory
```
cd /
```
* now open the yamal file to make edits by entering the command below
```
nano backend.gitlab-ci.yml
```

### Pending Error for pipeline possible solutions
* enabled shared runners both of them
* ran the gitlab-runner. (Start from step one in Mitra's powerpoint to the step where you run it.)

#### Backend paths
* coms-309-kk-09.cs.iastate.edu:8080/user/ - this path is used when getting all users - GET
```
format:
    Array of JSON Objects with attributes seen in user/add POST

```

* coms-309-kk-09.cs.iastate.edu:8080/user/add - this path is used when adding a user - POST
```
format:
{
    "userID": 7,
    "username": "Testingpassword",
    "password": "pass",
    "leaderboard": 0,
    "firstname": "jack",
    "lastname": "Kinser",
    "phone": "55566565"
}

```
* coms-309-kk-09.cs.iastate.edu:8080/user/get
*this path is used when getting a users info by their username
```
username

```

* coms-309-kk-09.cs.iastate.edu:8080/game/add - this path is used when adding a new game
* used when a user creates a new game save
```
{
    "gameid":0,
    "score": 0,
    "sport": "football",
    "username": "richard",
    "savename": "game3"
}

```
* coms-309-kk-09.cs.iastate.edu:8080/game - this path is used when getting all games, used
* for testing purposes, not useful in app
```


```
* coms-309-kk-09.cs.iastate.edu:8080/game/get - this path is used when getting all games
* for a user
```
username

```



* coms-309-kk-09.cs.iastate.edu:8080/store/add
*this path is used when a user buys a store item
```
format:
{
    "storeid": 0,
    "username": "brady",
    "item": "tics"
}

```
* coms-309-kk-09.cs.iastate.edu:8080/store/get
*this path is used when getting a users store items by their username
```
username

```
* coms-309-kk-09.cs.iastate.edu:8080/schedule/add
*this path is used when adding to the schedule
*when adding teams, add in alphabetical order
```

{
    "gameid": 0,
   "sport" : "football",
   "date" : 10122021,
   "teams" : "bearsZpackers"

}
```
* coms-309-kk-09.cs.iastate.edu:8080/schedule/get
*this path is used to get schedule
```
no format
```

* coms-309-kk-09.cs.iastate.edu:8080/prediction/add
*this path is used to add a prediction a user has for a match
```
{
    "predictionid":0,
    "date": 11092020,
    "sport": "football",
    "teams":"bearsZPackers",
    "predictionForGame":"win",
    "username": "richard",
    "savename": "game3"
}
```

* coms-309-kk-09.cs.iastate.edu:8080/prediction/get
*this path is used to get all predictions a user has made
```
username
```
* coms-309-kk-09.cs.iastate.edu:8080/prediction/getByMatch
*this path is used to get a prediction a user has for a specific match
```
savename/username/bearsZPackers
```

* coms-309-kk-09.cs.iastate.edu:8080/websocket/username
*this path is used to reach the chat feature 
```
coms-309-kk-09.cs.iastate.edu:8080/websocket/username

```
### Matchplay
* Select Sport (football) -> Select Game (Packers vs Vikings) -> Select Team (I select Vikings to win)  ->
If win add points, If lost no points (Vikings win I get 20 points, Vikings lose no points) 

* New game -> Select Sport (football) -> Select match (Packers vs Vikings) -> Select Team (I select Vikings to win)  ->
If win add points, If lost no points (Vikings win I get 20 points, Vikings lose no points) 