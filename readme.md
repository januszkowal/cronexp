# Preface

Command line 'cronexp' application parses a cron string and expands each field to show the times at which it will run.
The cron string should be passed to the application as a single argument, e.g.

<pre>
~$ cronexp "*/15 0 1,15 * 1-5 /usr/bin/find"
</pre>

The output is formatted as a table with the field name taking the first 14 columns and the times as a space-separated
list following it.

For example, the following input argument:
<pre>*/15 0 1,15 * 1-5 /usr/bin/find</pre>

Prints the following output:
<pre>
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
</pre>

Application supports:
- \* as 'every value' for specified part
- lists, eg. 1,2,3
- ranges, e.g 1-3
- intervals, e.g. 1/3, */15
- ranges of values and lists in one "cron part" e.g. 1,3,5-7,15-18
- aliases for **dayOfWeek** and **month**

## Aliases
All aliases are case-insensitive

### day of week

| Alias | Value |
|-------|-------|
| mon   | 1     |
| tue   | 2     |
| wed   | 3     |
| thu   | 4     |
| fri   | 5     |
| sat   | 6     |
| sun   | 7     |

### month

| Alias | Value |
|-------|-------|
| jan   | 1     |
| feb   | 2     |
| mar   | 3     |
| apr   | 4     |
| may   | 5     |
| jun   | 6     |
| jul   | 7     |
| aug   | 8     |
| sep   | 9     |
| oct   | 10    |
| nov   | 11    |
| dec   | 12    |

# Prerequisites

Application requirements:
- Installed Java 17+ compiler on classpath

# Cloning application
<pre>git clone git@github.com:januszkowal/cronexp.git</pre>

# Building
Having downloaded application repository it can be built and distributed using one of the following commands:

1. Create tar+zip

<pre>/gradlew assembleDist</pre>

2. Create zip only

<pre>/gradlew/distZip</pre>

The Distribution Plugin creates one or two archives: zip (+ tar optionally).
Their contents are identical and include the project jar, all dependency jars, and two scripts: Bash and .bat-file.

# Running

We can use <code>/build/distributions/cronexp/build/distributions/cronexp.zip</code> archive to run cronexp application

Before we run the application it must be unpacked first:
<pre>unzip cronexp.zip</pre>

and now it can be run via executable script:
<pre>./cronexp/bin/cronexp "*/15 0 1,15 * 1-5 /usr/bin/find"</pre>