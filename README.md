# Atlassian Statuspage API

This library is made to provide an easy usage of the REST API from statuspage.io made by Atlassian.
<br>
<br>[More information about statuspage](https://www.atlassian.com/software/statuspage)
<br>[Developer documentation](https://developer.statuspage.io)

<br>

# Table of contents

<ol>
    <li><a href="#getting-started">Getting started</a></li>
    <br>
    <ul>
        <strong>Objects in this library</strong>
        <li><a href="#pages">Pages</a></li>
        <li><a href="#components">Components</a></li>
    </ul>
    <br>
    <li><a href="#about-this-repository">About this Repository</a></li>
</ol>

## Getting started

### Obtaining the API Token

First, you need to obtain the api token for your page.
To get it, click on your profile and go to the API Info Site in your dashboard.

<img src="https://raw.githubusercontent.com/Taucher2003/README-Assets/main/atlassian-statuspage-api/api-info.png">

After that, there are two possibilities:
<ul>
    <li>You are the organization owner
    <p>Then, you are fine. You can create an API Key or see the list of currently active keys there.
    <img src="https://raw.githubusercontent.com/Taucher2003/README-Assets/main/atlassian-statuspage-api/api-keys-as-owner.png"></li>
    <br>
    <li>You aren't the owner of that organization
    <p>Then, you will need to ask the organization owner to create an API Key for you
    <img src="https://raw.githubusercontent.com/Taucher2003/README-Assets/main/atlassian-statuspage-api/api-keys.png"></li>
</ul>

<br>

### Adding the library to your project

Before you can continue with the coding, you need to add the library to your project. You can do that by downloading the
latest release and reference that library.
If you use Maven, you have it much easier. Then you just need to add the dependency to your `pom.xml`.
Don't forget to replace **VERSION** with an actual version. You can see the version numbers in the releases of this project.
```xml
<dependency>
  <groupId>com.github.taucher2003</groupId>
  <artifactId>atlassian-statuspage-api</artifactId>
  <version>VERSION</version>
</dependency>
```

<br>

### Creating your StatuspageAPI instance

<br>After obtaining your token, you need to create your instance of the StatuspageAPI.
<br>From this instance, you can execute your operations.
```java
StatuspageAPI api = new StatuspageAPI("api-token");
```

<br>

## Pages

Pages hold general information about your Statuspage. You can use this class to modify your page and the settings of it.

```java
Page page = api.getPage("page-id");
List<Page> pages = api.getPages();
```

<br>

## Components

Components are the monitored objects. You can use this class to modify their status and other values.

```java
Component component = page.getComponent("component-id");
List<Component> components = page.getComponents();
```

<br>
<br>

## About this Repository
<br>

### Commit Structure
Every commit will be prefixed with an emoji to indicate the type of the commit.
<br>Here you have a list of the emojis that are used.
```
⚡ Bugfixes
🎈 Small changes
🚀 New functions
📚 Documentation updates
🛸 New Release version
🔮 Readme Updates
```