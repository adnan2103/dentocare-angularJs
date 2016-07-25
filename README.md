
#############################################Dento care application#################################

################################Major issues before go live.########################################
##Fix bug in new appointment.
## Bug in Patient Update.

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

Access management & ROLE based access : Application modules and packages.
User ROLE and Access Maintenance
ROLES IN DENTOCARE (4 Currently)
    DENTOCAREADMIN
    CLINICADMIN
    CLINICUSER
    PATIENT
MODULES in Dento-care (7 currently)
    PMM : Patient Management Module
    TMM : Treatment Management Module
    TIMM : Treatment Image Management Module
    AMMALERTS : Appointment management Module with Alerts
    AMMNOALERTS : Appointment management Module without Alerts

    IMM : Invoces management Module.
    PRMM : Print management Module.

DentocareAdmin :
    As a dento care admin,
    I can setup Clinic Account as ClinicAdmin
    I can see list of such clinic's account created with basic information.
    In detail page I can see the packages what they have purchased and when they are expiring.
    I can add more packages or extend expiry by adding payment for a clinic.
    I can create new clinic users ClinicUser and give access to purchased modules.
    By default Clinic User will have access to all purchased modules.
    ClinicAdmin can see all the links in application but can access to only purchased modules, for non purchased modules
    or expired modules he will get a message to purchase it.
    ClinicUser will see or have access to modules for which access is given and not expired.


New Functionality :
Billing[IMM] : As a clinic user I can generate daily/weekly/monthly/yearly invoices and print it.
Print[PMM] : As a clinic user I can configure & print the patient report card. (What details he wants to print in patient card)
As a clinic user I can add Patient Medical History on Treatment Page.
Personalizations for Logged in User welcome message with Name,
As a clinic user I want to call/email patient directly from dento care application.
As a clinic user I want to share an excellent case on FB ,or to get the patient feedback and comments and rating.

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
