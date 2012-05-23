ANT=ant
BUILDXML=build.xml
PATH:=${PATH}:/usr/share/java/apache-ant/bin

all: $(BUILDXML)
	$(ANT) -f $<

$(BUILDXML):
	$(error $(BUILDXML): No such file!)

%: $(BUILDXML)
	$(ANT) -f $< $@
