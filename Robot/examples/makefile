jdk = C:/java/jdk1.5.0/bin
javadoc = C:/java/jdk1.5.0/bin/javadoc
javac = $(jdk)/javac
jar = $(jdk)/jar
java = $(jdk)/java
obscufJar = D:/Java/proguard3.3/lib/proguard.jar

beckerJar = ../build1.5/dist/becker.jar
out	= classes
src	= .
dist	= dist
doc	= doc

# All the .java files
javas := $(wildcard \
				$(src)/ch*/*/*.java \
			)

# All the .class files corresponding to a .java file
classes = $(subst .java,.class,$(javas))

	
examples:  $(out)/compileTimestamp

$(out)/compileTimestamp:	$(javas)
	$(javac) -deprecation -Xlint:unchecked \
              -classpath "$(out);$(beckerJar)" \
              -sourcepath "$(src)" \
              -d "$(out)" \
              $(filter %.java, $?)
	touch $(out)/compileTimestamp	

