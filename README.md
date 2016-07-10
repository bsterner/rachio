##Secret Santa Assignment
=========================

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

### Running the application
You will need to install lombock if you intend to run this in an IDE.  Otherwise you can just run via maven.


### Design Notes & Considerations

No data persistence framework or Database was used given the time-constraint.  In a real production environment we would've opted to leverage some open-source solution such as MyBatis, Hibernate, TopLink, EclipseLink, etc... along with a relational DB such as MySQL, Oracle, Postgres, etc... or NoSQL (non-relational) DB such as MongoDB, Cassandra, HBase, etc...

