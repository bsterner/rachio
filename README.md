##Secret Santa Assignment
=============================

####Description:

This project was created for the secret santa assignment as part of the interview process with Rachio.

### Pre-requisites
* Maven: version 3.2+
* JDK: version 1.8+
* Internet connection 

#### Part I Notes

* This was easy enough.  Secret Santa list was implemented using the Controller / Service / Repository pattern associated with SpringMVC.  Controller would be the entry point to the application (web service if deployed as web app), Service would be the Business Service Component and Repository is the model or data component.

* There are 2 input options:
<blockquote>
<ol>
<li>Call Controller.createAssignments() passing an empty list.  This will a default list constructed in the background.</li>
<li>Call Controller.createAssignments(participants) passing a list of participants to be used to create the random secret santa list.</li>
</ol>
<p>
</blockquote>
In either case, the call will return a Map (asssociative array) of values where the key represents the individual that is the secret santa and they key the individual they are assigned to get a gift for.</p> 

### Part II Notes (Summarized as bullet points)

* Maybe this was the point of the exercise but this went from simple and straight forward to supremely difficult by addition of the constraints.
* Based on some googling around this seems to be an extremely difficult mathematical challenge involving graph theory and specifically the reduction in path traversal. There were topics/sources referenced such as NP complete systems, turing machines, hamilton circuits and communicating sequential processes.  From what I can gather the only solution to this is a brute force method.

* Just to rule it out, I first tried to implement a solution on my own using set arithmetic where I stored the participants as keys in a map and a list of the previous years' secret santas as the values.  It was then simply a matter of basic set arithmetic (add/remove) and randomizing to get this assignments.  I found that by trial and error I could get the tests to sometimes pass depending on how the lists were shuffled.  Although one solution might be to iterate/try/catch this until I got a randomized list that could be successfully assigned, I really didn't like the idea of "settling" for such an aesthetically unpleasing solution.  I also felt like as the inputs got more complex, the system would began to break down exponentially.
* I thought about trying to use database tables, but the inherent problem doesn't disappear it just moves it to another layer.
* This led me to search for an existing API and try to incorporate it into my application.  Common software problems occur all throughout business which have been already been sufficiently solved.  A good example of this are the Apache APIs (commons, logging, io, etc...)
* I found choco3 as a possible solution, evaluated the SecretSanta example which had "similar" constraints and decided to go this route.  What I found was that a) I spent the majority of my time just trying to understand how the API actually works (I didn't want to feel like I was just cutting and pasting code); b) I understood it enough to incorporate it into my design; c) I ran out of time to get any further than part 2 and my test cases and the ability to really build out the entire application suffered.

### Design Notes & Considerations
Below is a list of things I would change/enhance given more time:
<blockquote>
* Understand the API better in order to implement part 3
* Created more test cases with variable inputs to improve the functionality of the application
* Used Spring to inject interface implementations (for example SecretSantaService) as well as input test data
* Created a data source either with an in-memory database, the flatpack API and some text files and used a persistance framework like myBatis or Hibernate
* Created performance tests to evaluate timing
* As always, documentation could be better :)
</blockquote>

### Running the application
You will need to install lombock if you intend to run this in an IDE.  Otherwise you can just run via maven.

