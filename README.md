
#############################################Dento care application###############################################################

################################Major issues before go live.########################################

##1
##UI Validation messages.
##Service Success/Failure Messages.
##adding javax.validation annotations and messages in response.
## Auto move focus to validation messages and success messages.
## Required fields validations.(Name, Gender, Mobile no in Add/Update patient AND chief complain in Add/Update Treatment)

##2
##Patient Photo upload immediately after creating patient.

##3
## Pre and post treatment photos upload and display library.

##4
## CSS and alignment, apply theme to entire application. http://themeforest.net/
## Add Payment and Oral examination + button styling.
###################### Low priority ######################################################################

## Adding multiple treatments, low priority.
## Treatment Start and End Date.?
## Fields for create_ts, by, modified_ts ,by

########FUTURE ITEMS TO COME ###########################################################################
Implement Caching
Set up liquibase
Change App folder and packaging structure
Setup unit, integration, functional tests.
Write frontend tests.


Saving and Showing Patient E-mail Id.
Applying discount or offer code.

Doctor can refer patient to other doctor, don't show treatment costs & payments of one doc to other.
Access management & ROLE based access
User Maintenance

Doctor can configure & print the patient report card.

Creating Patient login credentials.
Sending Patient login credentials via email & sms.
Asking Patient to change password after first use.

Appointment Diary, Day/Week/Month schedule, reminders to doctor and patient.

Letting Doctor to call patient from application and sending emails.

Got the excellent case, share it on FB or to other doctor, get the patient feedback and comments and rating.

################################### DONE #####################################################################

##DONE## immediately refreshing uploaded photo each time.
##DONE## Default logged in page does not show list of patients.
##DONE## Total treatment cost display.
##DONE## Total amount paid and pending amount display.

##DONE## order by issues.(by id on home page)
##DONE## CSS and alignments on create and update patient.
##DONE## CSS and alignment on patient photo upload.

##DONE## Date of birth format issue in all pages.
##DONE## Payment date is being saved with proper value and it always shows current date.

##DONE## Mobile number validation issue.(should be 10 digit number.)
##DONE## Date of birth and Payment date format(dd-MM-yyyy) and validation issue.

##DONE## Add or update patient UI modification work and controller update is pending.
##DONE## Add or update Treatment UI modification work and controller update is pending.

##DONE## Login page is not mobile friendly.
##DONE## List of Patients Page :

##DONE## Create/update patient along with user credentials and patient doctor mapping.
##DONE## Get list of all patients basic details without treatment details for a logged in doctor.
##DONE## Search a patient based on name or phone number search parameters.


##DONE## Patient and Treatment Details Page :

##DONE## Get Treatment details for a selected patient.
##DONE## Add/update treatments to patient.
##DONE## Add/update oral examinations to treatment.
##DONE## Add/update payment to treatment.

##DONE## 7. Spring security with Basic Authentication.
