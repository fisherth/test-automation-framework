package com.notsruht.taf.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.notsruht.taf.util.TAFProperties;

import io.cucumber.core.plugin.HtmlFormatter;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;

/**
 * Creates a Cucumber report in a directory on the filesystem. The report is named "tafReport_{timestamp}.html", where {timestamp} is the time the Test Suite begins to run. 
 * If the <i>testautomationframework.reportFileDirectory</i> property has a value, then the report will be saved there. 
 * If it does not have a value, then the report will be saved in {user.home}\.tafReports\{applicationName} where {user.home} is the current user's home directory and 
 * {application.name} is the value of the <i>testautomationframework.application.name</i> property.  
 */
public class TAFHtmlFormatter implements ConcurrentEventListener {
	private static final String REPORT_DIRECTORY_PROPERTY = "testautomationframework.reportFileDirectory";
	private static final String APPLICATION_NAME_PROPERTY = "testautomationframework.application.name";
	private static final String DEFAULT_REPORT_PREFIX = "tafReport";
	private HtmlFormatter formatter;

	public TAFHtmlFormatter() throws IOException {
		String filename = getFilename();
		File reportDirectory = getReportDirectory();		
		reportDirectory.mkdirs();
		File reportFile = new File(reportDirectory, filename);
		reportFile.createNewFile();
		
		formatter = new HtmlFormatter(new FileOutputStream(reportFile));
	}
	
	/**
	 * The filename of the report file. This implementation will name it <i>tafReport_{timestamp}.html</i>. Subclasses can override this method to provide their own logic for compiling the filename.
	 * 
	 * @return The filename of the report file.
	 */
	protected String getFilename() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss.SSSZ");
		return DEFAULT_REPORT_PREFIX + "_" + sdf.format(now) + ".html";
	}
	
	/**
	 * The directory the report files get saved to. If there is a value in the <i>testautomationframework.reportFileDirectory</i> property, 
	 * then the report will be saved there. Otherwise, it is the user home directory \ .tafReports \ the value of the <i>testautomationframework.application.name</i> property.
	 * Subclasses can override this method to provide their own logic for compiling the save directory.
	 * 
	 * @return The directory where the report file is saved.
	 */
	protected File getReportDirectory() {		
		String reportDirectoryInProperties = TAFProperties.getInstance().getProperty(REPORT_DIRECTORY_PROPERTY);		
		
		if(reportDirectoryInProperties != null && !reportDirectoryInProperties.isBlank() ) {
			return new File(reportDirectoryInProperties.trim());
		} else {
			String applicationName = TAFProperties.getInstance().getProperty(APPLICATION_NAME_PROPERTY);
			Path path = Paths.get(System.getProperty("user.home"), ".tafReports", applicationName.trim());
			return path.toFile();
		}				
	}

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		formatter.setEventPublisher(publisher);
	}
}