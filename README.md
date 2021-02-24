# Academy Simulator

A group project assigned by Sparta Global to simulate the company and its expansion, with trainees being the agents
whereby each trainee is either assigned to the waiting list or a training centre.

## Sprint 0

### Planning

Initially we created user stories that can be found here on
our [project board](https://github.com/kxrtiswithak/AcademySimulator/projects/3) alongside our project progress. Upon
the creation of the user stories we used estimation using t-shirt sizing. Using
these [results](https://www.planitpoker.com/board/#/room/a2a55c477cb8411fb545a9aaac1e6b96) we were able to determine
that some of our user stories were too akin to Epics, we therefore had to reduce these down to be more clear and consise
in order to fit the true nature of a user story.

### Review

Within the review the client was able to express their opinions of the user stories and we were able to adapt current stories
 and make new stories based on this feedback.

## Sprint 1

### Requirements

- The tracker needs to be able to track time in a consistent way.
- The program starts by asking how long the simulation will run for.
- Every month, a random number of trainees are generated wanting to be trained (20 -30).
- Every 2 months, Sparta global opens a training centre. They open instantly and can take trainees every month
- A centre can train a max of 100 trainees and takes a random number of trainees every month. (0 - 20 trainees per month
  up to the max capacity).
- If a centre is full, trainees can be moved to any other centre which is not full
- If all centres are full, the trainees go onto a waiting list.
- This list must be served first before new trainees are taken.
- At the end of the simulation, output should;
    - number of open centres
    - number of full centres
    - number of trainees currently training
    - number of trainees on the waiting list

### Planning

A lot of our planning had already been thoughtout during Sprint 0, so it was just a case of assigned tickets to each member of the group, some people became testers and some developers. We therefore 
used two seperate branches, `test` and `dev` in order to appropriately seperate the two paths within git.

### Execution

Whilst coding we made sure to create a new branch for the feature and had a path ordering to be `[feature] -> dev -> test -> master`, such that there was no lost code and the features could finalise as being
tested before being submitted to master. We also made sure to communicate with each other and work on arrising issues jointly to make the process more streamlined. Pull requests were used alongside code reviews 
ensuring that the code was correct and there were no conflicts with the branch being pushed to.

### Review

In the review we analysed the project board to decifer what went well and what could have been improved. As well as this we showed the client the program. 
Some queries about of final product for this sprint were expressed by the client and improvements were suggested, which we began work on immediately allowing for all issues to be corrected 
and fully tested.

### Retrospective

During the retrospective we came to the realisation that our time-management was our main downfall. We improved in the next sprint 
by maintaining organisation and dividing people into more specific teams, whilst ensuring there are no bottlenecks.

## Sprint 2

### Requirements

- Richard will now check centres each month. If a centre has less than 25 trainees, it will close. The trainees will be randomly moved to another suitable centre
- The simulation should now offer the choice of data at the end of the simulation or a running output updated each month
- Trainees will now have a course type (Java, C#, Data, DevOps or Business). A trainee will be randomly assigned a course when they are created. This will never change
- Sparta now has 3 different types of centre. When a new centre can be opened, one of the following will be randomly chosen
- Training Hub: can train a maximum of 100 trainees but 3 can be opened at a time each month
- Bootcamp: can train a maximum of 500 trainees but can remain open for 3 months if there are less than 25 trainees in attendance. 
If a Bootcamp has 3 consecutive months of low attendance, it will close. For the lifetime of the simulation, only 2 Bootcamps can ever exist
- Tech Centre: Can train 200 trainees but only teaches one course per centre. This is chosen randomly when a Tech Centre is open
- The simulation should report on the following:
  - number of open centres (breakdown for each type)
  - number of closed centres (breakdown for each type)
  - number of full centres (breakdown for each type)
  - number of trainees currently training (breakdown for each type)
  - number of trainees on the waiting list (breakdown for each type)

### Planning

Much akin to Sprint 0, we created new user stories for the updated requirements. We had split up for this process as some requirements were still missing from the previous Sprint and tests still needed to
 be completed for the remainder of the code. We then spoke to the client to make certain that all the user stories were correct, clear and diverse enough.

### Execution



### Review



### Retrospective


