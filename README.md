
#############################################Dento care application#################################

################################Major issues before go live.########################################

##Add more fields for mobile numbers.

##Letting enter Age or DOB while creating new patient.

##Adnan : Model for appointment treatment, Accordian for treatment page, save individual treatment, date format.

##Login Screen and All responsive UIs

##Back buttons : (Use "angular-ui-router" instead route provider.)

##Optimize Web server calls.

########LIVE$$$$$$$$$$$$$$$$S

##Add most recent treatment status on home page and add a filter to filter records based on treatment status.
(On going, Closed, Not started, All)

## SMS/Email reminders to doctor and patient.

###################### Low priority ######################################################################

## Pagination for list of patients.
## Sort or show patients based on Selected Alphabet to have quick search.

##Delete Functionality : Payment, Oral examination, treatment.

##UI Validation messages on screen instead pop ups.
##Service Success/Failure Messages.
##adding javax.validation annotations and messages in response.
## Auto move focus to validation messages and success messages.
## Required fields validations.(Name, Gender, Mobile no in Add/Update patient AND chief complain in Add/Update Treatment)

##Enable XSRF token functionality which is disabled due to treatment imagages upload function.

## Convert Patient as directive
##Patient Photo upload immediately after creating patient.
##Auto refresh pre and post treatment uploaded images.

## Saving and Showing Patient E-mail Id.

########FUTURE ITEMS TO COME ###########################################################################

Dento-care users management, billing cycle and billing maintenance.

Invoice Management : Clinic User can generate daily/weekly/monthly/yearly invoices and print it.

Let the Clinic Sign up and setup their account/profile in dento care.
Buy the package and do setup from : Clinic settings tab.

Patient Medical History on Treatment Page.
Personalizations and Logged in Doctor welcome message and information, clinic information,

Access management & ROLE based access : Application modules and packages.
User ROLE and Access Maintenance

Doctor can configure & print the patient report card. (What details he wants to print in patient card)
Generate monthly payment collection report.

Letting Doctor to call/email patient from application.

Got the excellent case, share it on FB or to other doctor, get the patient feedback and comments and rating.

###############ALM and CICD Task ############################################################################
Implement Caching
Set up liquibase
Change App folder and packaging structure.
restructure the angular code.
Setup unit, integration, functional tests.
Write frontend tests.
Automate Build and Deployment.

################################### DONE #####################################################################
##DONE## Appointment Diary, Day/Week/Month schedule.
##DONE## Add functionality to add more treatment.
##DONE## Responsive alignment of treatment’s tables.
##DONE## Responsive alignment of pre and post treatment images.
##DONE## Pre and post treatment photos upload and display library.
##DONE## Treatment Start and End Date.?
##DONE## Fields for create_ts, by, modified_ts ,by
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
