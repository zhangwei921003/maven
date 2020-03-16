package com.sample.plugin;

import com.sample.plugin.enums.Color;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * say hi to users
 */
@Mojo(name = "sayhi")
public class GreetingMojo extends AbstractMojo {

  @Parameter(property = "sayhi.greeting", defaultValue = "Hello World!")
  private String greeting;
  @Parameter
  private boolean myBoolean;
  @Parameter
  private Integer myInteger;
  @Parameter
  private Double myDouble;
  @Parameter
  private Date myDate;
  @Parameter
  private File myFile;
  @Parameter
  private URL myURL;
  @Parameter
  private Color myColor;
  @Parameter
  private String[] myArray;
  @Parameter
  private List myList;
  @Parameter
  private Map myMap;
  @Parameter
  private Properties myProperties;

  @Override
  public String toString() {
    return "GreetingMojo{" +
        "greeting='" + greeting + '\'' +
        ", myBoolean=" + myBoolean +
        ", myInteger=" + myInteger +
        ", myDouble=" + myDouble +
        ", myDate=" + myDate +
        ", myFile=" + myFile +
        ", myURL=" + myURL +
        ", myColor=" + myColor +
        ", myArray=" + Arrays.toString(myArray) +
        ", myList=" + myList +
        ", myMap=" + myMap +
        ", myProperties=" + myProperties +
        '}';
  }

  public void execute() {
    getLog().info(toString());
  }

  public File getMyFile() {
    return myFile;
  }

  public void setMyFile(File myFile) {
    this.myFile = myFile;
  }

  public URL getMyURL() {
    return myURL;
  }

  public void setMyURL(URL myURL) {
    this.myURL = myURL;
  }

  public Color getMyColor() {
    return myColor;
  }

  public void setMyColor(Color myColor) {
    this.myColor = myColor;
  }

  public String[] getMyArray() {
    return myArray;
  }

  public void setMyArray(String[] myArray) {
    this.myArray = myArray;
  }

  public List getMyList() {
    return myList;
  }

  public void setMyList(List myList) {
    this.myList = myList;
  }

  public Map getMyMap() {
    return myMap;
  }

  public void setMyMap(Map myMap) {
    this.myMap = myMap;
  }

  public Properties getMyProperties() {
    return myProperties;
  }

  public void setMyProperties(Properties myProperties) {
    this.myProperties = myProperties;
  }

  public boolean isMyBoolean() {
    return myBoolean;
  }

  public void setMyBoolean(boolean myBoolean) {
    this.myBoolean = myBoolean;
  }

  public Integer getMyInteger() {
    return myInteger;
  }

  public void setMyInteger(Integer myInteger) {
    this.myInteger = myInteger;
  }

  public Double getMyDouble() {
    return myDouble;
  }

  public void setMyDouble(Double myDouble) {
    this.myDouble = myDouble;
  }

  public Date getMyDate() {
    return myDate;
  }

  public void setMyDate(Date myDate) {
    this.myDate = myDate;
  }

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
}
