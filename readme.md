### Preface:
Command line 'cronexp' application parses a cron string and expands each field to show the times at which it will run.
The cron string should be passed to the application as a single argument, e.g.

<pre>
~$ cronexp "*/15 0 1,15 * 1-5 /usr/bin/find"
</pre>

The output is formatted as a table with the field name taking the first 14 columns and the times as a space-separated list following it.

For example, the following input argument:
<pre>*/15 0 1,15 * 1-5 /usr/bin/find</pre>

Prints  the following output:
<pre>
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
</pre>

### Prerequisites:

Building application requires:
- Java 17+ compiler on classpath

### Building
Having downloaded application repository it can be build using one of the following commands:

1. Create tar+zip
<pre>/gradlew assembleDist</pre>

2. Create zip only
<pre>/gradlew/distZip</pre>

The Distribution Plugin creates one or two archives: zip (+ tar optionally). 
Their contents are identical and include the project jar, all dependency jars, and two scripts: Bash and .bat-file.

### Running
We can use <code>/build/distributions/cronexp/build/distributions/cronexp.zip</code> archive to run cronexp application

Before we run the application it must be unpacked first:
<pre>unzip cronexp.zip</pre>

and now it can be run via executable script:
<pre>./cronexp/bin/cronexp "*/15 0 1,15 * 1-5 /usr/bin/find"</pre>