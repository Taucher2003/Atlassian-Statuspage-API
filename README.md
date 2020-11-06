# Atlassian Statuspage API

This library is made to provide an easy usage of the REST API from statuspage.io made by Atlassian.
<br>
<br>[More information about statuspage](https://www.atlassian.com/software/statuspage)
<br>[Developer documentation](developer.statuspage.io)

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

<br>First, you need to create your instance of the StatuspageAPI.
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
Component component = page.getComponent("page-id");
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