For the Lab4 client:

*Entry Point: http://localhost:8080/lab4-mzomorod

*To calculte grade: /calculate?year=<year>&subject=<subject>

*To map letter grade: /map?grade=<grade>

*To get subjects: /get

It was not clear in the requirements if the source code used to generate the aar and war files should be included in the submission, therefore they were included as a precaution. the aar service file is located in the task1 directory, and the war client file is located in the task2 directory. The client and service directories contain the source code used to generate the files.

The war client file was created with the axis libraries included in the WEB-INF/lib directory in order to be found within the web application context during Tomcat deployment. In order to not include the axis2 libraries in the submission, the war file was not deployed/built with Ant. Therefore the grader must build the war file file using "ant deploy" from the command line from within the task2 directory. The tomcat.home and axis2.home variables must also be configured with the grader's path in the build.properties file.