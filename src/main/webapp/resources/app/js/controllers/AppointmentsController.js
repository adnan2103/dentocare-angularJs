'use strict';

/**
 * AppointmentsController
 * @constructor
 */
var AppointmentsController = function($scope, $document, $http) {

    $scope.fetchAppointments = function() {

        $http.get('appointments').success(function(appointments){
            $scope.appointments=appointments;

            var $jq = jQuery.noConflict();

            $document.ready(function() {
                // page is now ready, initialize the calendar...

                $jq('#calendar').fullCalendar({
                    // put your options and callbacks here

                    height: 450,
                    defaultView : 'agendaWeek',
                    businessHours : {
                        start: '09:00',
                        end: '21:00',
                        dow: [ 1, 2, 3, 4, 5, 6 ]
                    },
                    //selectable : true,
                    //selectHelper : true,
                    editable : true,
                    header: {
                        left: 'prev,next, today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    /*select : function(start, end, jsEvent, view ) {

                     var events = new Array();
                     event = new Object();
                     event.title = 'Hello';
                     event.start = start;
                     event.end = end;
                     event.allDay = false;
                     events.push(event);
                     $jq('#calendar').fullCalendar('addEventSource', events);
                     },*/
                    eventResize: function(event, delta, revertFunc) {
                        alert('Appointment resize. '+event.id);
                    },
                    eventDrop: function(event, delta, revertFunc) {
                        alert('Appointment dropped. '+event.id);
                    },
                    /*dayClick: function(date, view) {
                     alert('Clicked on: ' + date.format());
                     alert('Current view: ' + view.name);
                     $jq(this).css('background-color', 'red');
                     },*/
                    events: $scope.appointments
                })

            });
        })
    }
    $scope.fetchAppointments();

};