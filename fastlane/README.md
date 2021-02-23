fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android bump_version
```
fastlane android bump_version
```
Bump build number and commit
### android commit_bump_version
```
fastlane android commit_bump_version
```
Commit bump build number
### android set_version
```
fastlane android set_version
```
Set version name
### android get_version
```
fastlane android get_version
```
Print current app version
### android staging
```
fastlane android staging
```
Deploy a new version of staging to the Google Play
### android production
```
fastlane android production
```
Deploy a new version of production to the Google Play
### android appDistribute_all
```
fastlane android appDistribute_all
```
Deploy a new version to Firebase App Distribution Staging/Production
### android appDistribute_staging
```
fastlane android appDistribute_staging
```
Deploy a new Staging version to Firebase App Distribution
### android appDistribute_production
```
fastlane android appDistribute_production
```
Deploy a new Production version to Firebase App Distribution
### android all
```
fastlane android all
```
Deploy a new version of staging&production to the Google Play

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
