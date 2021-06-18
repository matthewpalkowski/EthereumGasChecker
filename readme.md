# Ethereum Gas Checker

Application designed to monitor Ethereum gas prices as reported by https://etherscan.io/. User can
set a threshold for Ethereum gas prices at which they wish to be notified so the user can take 
whatever action they may want with their mining setup.

## System requirements

Application is written in Kotlin so the user must have a Java Runtime Environment installed on the 
target device (download link at https://www.java.com/en/download/).

## Installation

Clone or download the package and extract it to a local device drive. If your system does not have
a copy of Java Runtime Environment, use download link above.

## Using the Ethereum Gas Checker
### Scanning Gas Prices
The Ethereum Gas Checker is fairly straightforward to use. Scanning can be started and stopped by 
pressing the top-most button "Start Scanning". Once started, the application will begin scanning
Ethereum gas prices and will display the most recent price. If the Gas Threshold and duration 
conditions are met, the application will send a notification to your system to alert you that the
gas price has risen/fallen below the provided values. 

### Setting Preferences
The threshold values used by the scanning feature are set to default on initial start-up of the 
program, however they can be customized if desired. This can be navigating to the Preferences 
page by clicking the "Set Preferences" button and entering the new desired values. Below is a 
brief description of what each of the effect each of the values within the preferences screen.

Gas Threshold: The GWEI gas price that serves as the price threshold that triggers a notification
when it is reached/crossed and remains at or beyond that value for a duration longer than the 
duration defined by Duration Threshold.

For example, say that the Gas Threshold is set to 10 and Duration Threshold is set to 30 seconds. 
If the gas price goes from 9 to 10 and stays at 10 for at least 30 seconds, a notification will be
sent stating that the gas price has reached the defined threshold. Similarly, if the price was to 
fall from 10 back to a price of 9, and remains at 9 for another 30 seconds, another notification 
would be sent with the message that the gas price had fallen below the threshold.

Scan Frequency: This defines how often the application should query the gas price. Few queries will
results in less consumption of system resources. Note that the gas price will only update a maximum 
of once every 10 seconds, so values less the 10 are not recommended.

Duration Threshold: This value defines how long the gas value should remain beyond the Gas Threshold
before a notification is sent to the user. Shorter intervals will provide more immediate feedback, 
however small intervals can lead to frequent notifications should the gas price happen to fluctuate 
around the Gas Threshold.

## Donations

If this was helpful or you want to support this project, please feel free to throw a donation my way.

Paypal: https://paypal.me/MatthewPalkowski?locale.x=en_US

Ethereum wallet: 0x9F657ed61D9C733BAe475f319d89616cc6ba0f66