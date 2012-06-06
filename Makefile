ANT=ant
PLANTUML=plantuml
BUILDXML=build.xml
PATH:=${PATH}:/usr/share/java/apache-ant/bin

all: $(BUILDXML)
	$(ANT) -f $<

.PHONY: doc
doc: doc/luokkarakenne.plantuml
	$(PLANTUML) $<

.PHONY: javadoc
javadoc: $(BUILDXML)
	$(ANT) -f $< $@

$(BUILDXML):
	$(error $(BUILDXML): No such file!)

%: $(BUILDXML)
	$(ANT) -f $< $@
