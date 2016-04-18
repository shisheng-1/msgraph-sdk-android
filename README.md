# Get started with the Microsoft Graph SDK for Android

[ ![Download](https://api.bintray.com/packages/microsoftgraph/Maven/msgraph-sdk-android/images/download.svg) ](https://bintray.com/microsoftgraph/Maven/msgraph-sdk-android/_latestVersion)[![Build Status](https://travis-ci.org/microsoftgraph/msgraph-sdk-android.svg?branch=master)](https://travis-ci.org/microsoftgraph/msgraph-sdk-android)

## Overview

This client library is a release candidate and is still in preview status, please continue to provide [feedback](https://github.com/microsoftgraph/msgraph-sdk-android/issues/new) as we iterate towards a production supported library.

Integrate the [Microsoft Graph API](https://graph.microsoft.io/en-us/getting-started) into your Android application!


## 1. Installation
### 1.1 Install AAR via Gradle
Add the maven central repository to your projects build.gradle file then add a compile dependency for com.microsoft.graph:msgraph-sdk-android:0.9.+

```gradle
repository {
    jcenter()
}

dependency {
    // Include the sdk as a dependency
    compile('com.microsoft.graph:msgraph-sdk-android:0.9.+')

    // Include the gson dependency
    compile('com.google.code.gson:gson:2.3.1')
}
```

## 2. Getting started

### 2.1 Register your application

Register your application by following [these](https://graph.microsoft.io/en-us/app-registration) steps.

### 2.2 Create an IAuthenticationProvider object

An instance of the **GraphServiceClient** class handles building requests,
sending them to Microsoft Graph API, and processing the responses. To create a
new instance of this class, you need to provide an instance of
`IAuthenticationProvider` which can authenticate requests to Microsoft Graph.

For an example of authentication in a client application see the [MSGraph SDK Android MSA Auth for Android Adapter](https://github.com/microsoftgraph/msgraph-sdk-android-msa-auth-for-android-adapter).

### 2.3 Get a GraphServiceClient object

Once you have set the correct application Id and url, you must get a **GraphServiceClient** object to make requests against the service. The SDK will store the account information for you, but when a user logs on for the first time, it will invoke UI to get the user's account information.

```java
final IClientConfig mCLientConfig = DefaultClientConfig.createWithAuthenticationProvider(mAuthenticationProvider);

final IGraphServiceClient mClient = new GraphServiceClient
                                            .Builder()
                                            .fromConfig(mClientConfig)
                                            .buildClient();
```

## 3. Make requests against the service

Once you have an GraphServiceClient that is authenticated you can begin making calls against the service. The requests against the service look like our [REST API](https://graph.microsoft.io/en-us/docs).

### Get the drive

To retrieve a user's drive:

```java
graphClient
    .getMe()
    .getDrive()
    .buildRequest()
    .get(new ICallback<Drive>() {
  @Override
  public void success(final Drive result) {
    final String msg = "Found Drive " + result.id;
    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT)
        .show();
  }
  ...
  // Handle failure case
});
```

For a general overview of how the SDK is designed, see [overview](docs/overview.md).

## 4. Documentation

For a more detailed documentation see:

* [Overview](docs/overview.md)
* [Extensibility](docs/extensibility.md)
* [Collections](docs/collections.md)
* [Errors](docs/errors.md)
* [Contributions](docs/contributions.md)

## 5. Issues

For known issues, see [issues](https://github.com/MicrosoftGraph/sdk-android/issues).

## 6. Contributions

The Microsoft Graph SDK is open for contribution. Please read how to contribute to this project [here](docs/contributions.md).

## 7. Supported Android Versions
The Microsoft Graph SDK for Android library is supported at runtime for [Android API revision 15](http://source.android.com/source/build-numbers.html) and greater. To build the sdk you need to install Android API revision 23 or greater.

## 8. License

Copyright (c) Microsoft Corporation. All Rights Reserved. Licensed under the [MIT license](LICENSE).

## 9. Third Party Notices

[Third Party Notices](THIRD PARTY NOTICES)
