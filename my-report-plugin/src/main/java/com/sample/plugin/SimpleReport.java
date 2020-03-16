package com.sample.plugin;

import java.util.Locale;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

@Mojo(
    name = "simple",
    defaultPhase = LifecyclePhase.SITE,
    requiresDependencyResolution = ResolutionScope.RUNTIME,
    threadSafe = true
)
public class SimpleReport extends AbstractMavenReport {

  @Override
  public MavenProject getProject() {
    return project;
  }

  public void setProject(MavenProject project) {
    this.project = project;
  }

  /**
   * Practical reference to the Maven project
   */
  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  public String getOutputName() {
    // This report will generate simple-report.html when invoked in a project with `mvn site`
    return "simple-report";
  }

  public String getName(Locale locale) {
    // Name of the report when listed in the project-reports.html page of a project
    return "Simple Report";
  }

  public String getDescription(Locale locale) {
    // Description of the report when listed in the project-reports.html page of a project
    return "This simple report is a very simple report that does nothing but "
        + "shows off Maven's wonderful reporting capabilities.";
  }

  @Override
  protected void executeReport(Locale locale) throws MavenReportException {

    // Get the logger
    Log logger = getLog();

    // Some info
    logger.info("Generating " + getOutputName() + ".html"
        + " for " + project.getName() + " " + project.getVersion());

    // Get the Maven Doxia Sink, which will be used to generate the
    // various elements of the document
    Sink mainSink = getSink();
    if (mainSink == null) {
      throw new MavenReportException("Could not get the Doxia sink");
    }

    // Page title
    mainSink.head();
    mainSink.title();
    mainSink.text("Simple Report for " + project.getName() + " " + project.getVersion());
    mainSink.title_();
    mainSink.head_();

    mainSink.body();

    // Heading 1
    mainSink.section1();
    mainSink.sectionTitle1();
    mainSink.text("Simple Report for " + project.getName() + " " + project.getVersion());
    mainSink.sectionTitle1_();

    // Content
    mainSink.paragraph();
    mainSink.text("This page provides simple information, like its location: ");
    mainSink.text(project.getBasedir().getAbsolutePath());
    mainSink.paragraph_();

    // Close
    mainSink.section1_();
    mainSink.body_();

  }
}
