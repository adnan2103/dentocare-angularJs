## save individual treatment,

#############################################Dento care application#################################
## Automated Build : Commit to Test it again.
################################Major issues before go live.########################################

## date format for treatment start and last modified date.
## SMS/Email reminders to doctor and patient.

##All Treatment Page [Add "Treatments" top bar link]
[We can fetch all treatments of logged in doctor with following details ]
Filter/Sorting can be applied to any column.
Treatment start, last modified, status(with Treatment Detail page link),Total Treatment Cost, Paid Amount, Pending Amount,
Patient Name(Patient Detail page Link), Mobile no.


##Access management & ROLE based access : Application modules and packages.

Dentocare_Admin :
    As a dento care admin,
    I can setup Clinic Account as ClinicAdmin
    I can see list of such clinic's account created with basic information.
    In detail page I can see the packages what they have purchased and when they are expiring.
    I can add more packages or extend expiry by adding payment for a clinic.
    I can create new clinic users ClinicUser and give access to purchased modules.
    ClinicAdmin can see all the links in application but can access to only purchased modules, for non purchased modules
    or expired modules he will get a message to purchase it.
    ClinicUser will see or have access to modules for which access is given and not expired.

##Optimize Web server calls.

########LIVE$$$$$$$$$$$$$$$$S

###################### Low priority Issue ######################################################################


## Fix bug in new appointment.
## Touch is not working in appointment.
## Accordian for treatment page,


##Back buttons : (Use "angular-ui-router" instead route provider.)


##Delete Functionality : Payment, Oral examination, treatment.

##UI Validation messages on screen instead pop ups.
##Service Success/Failure Messages.
##adding javax.validation annotations and messages in response.
## Auto move focus to validation messages and success messages.
## Required fields validations.(Name, Gender, Mobile no in Add/Update patient AND chief complain in Add/Update Treatment)

##Enable XSRF token functionality which is disabled due to treatment imagages upload function.

##Patient Photo upload immediately after creating patient.
##Auto refresh pre and post treatment uploaded images.

############### ALM and CI CD Task ############################################################################
Implement Caching
Set up liquibase
Change App folder and packaging structure, use maven plugin for bower task and js packaging, use gulp or grunt for js tasks and packaging.
Setup unit, integration, functional tests.
Write frontend tests.
Automate Build and Deployment.

########FUTURE ITEMS TO COME : Idea Board  ###########################################################################################

##Billing[IMM] : As a clinic user I can generate daily/weekly/monthly/yearly invoices and print it.
##Print[PMM] : As a clinic user I can configure & print the patient report card. (What details he wants to print in patient card)
##As a clinic user I can add Patient Medical History on Treatment Page.
##As a clinic user I want to call/email patient directly from dento care application.
##As a clinic user I want to share an excellent case on FB ,or to get the patient feedback and comments and rating.
## Pagination for list of patients.
## Sort or show patients based on Selected Alphabet to have quick search.
