'use strict';

/**
 * AppointmentsController
 * @constructor
 */
var AppointmentsController = function($scope, $document) {

    var $jq = jQuery.noConflict();

    $scope.eventId = 100;

    $scope.appointments = [
        {
            id     : 1,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Sunil : RCT',
            allDay : false, // will make the time show
            start  : '2016-07-07T11:30:00',
            end  : '2016-07-07T12:30:00',
            procedure : 'RCT'
        },
        {
            id     : 2,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Ramesh : RCT',
            allDay : false, // will make the time show
            start  : '2016-07-07T14:30:00',
            end  : '2016-07-07T15:30:00',
            procedure : 'RCT'
        },
        {
            id     : 3,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Suresh : RCT',
            allDay : false, // will make the time show
            start  : '2016-07-07T16:30:00',
            end  : '2016-07-07T17:30:00',
            procedure : 'RCT'
        },
        {
            id     : 4,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Naresh : RCT',
            allDay : false, // will make the time show
            start  : '2016-07-07T17:30:00',
            end  : '2016-07-07T19:30:00',
            procedure : 'RCT'
        }
    ];

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
              alert('Appointment resize.');
            },
            eventDrop: function(event, delta, revertFunc) {
              alert('Appointment dropped.');
            },
            /*dayClick: function(date, view) {
                alert('Clicked on: ' + date.format());
                alert('Current view: ' + view.name);
                $jq(this).css('background-color', 'red');
            },*/
            events: $scope.appointments
        })

    });
};