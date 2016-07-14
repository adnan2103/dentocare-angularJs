'use strict';

/**
 * AppointmentsController
 * @constructor
 */
var AppointmentsController = function($scope, $document) {

    var $jq = jQuery.noConflict();

    $scope.appointments = [
        {
            id     : 12345,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Sunil : RCT',
            allDay : false, // will make the time show
            url    :  '#/appointment/12345',
            start  : '2016-07-07T11:30:00',
            end  : '2016-07-07T12:30:00',
            procedure : 'RCT'
        },
        {
            id     : 12345,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Ramesh : RCT',
            allDay : false, // will make the time show
            url    :  '#/appointment/12345',
            start  : '2016-07-07T14:30:00',
            end  : '2016-07-07T15:30:00',
            procedure : 'RCT'
        },
        {
            id     : 12345,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Suresh : RCT',
            allDay : false, // will make the time show
            url    :  '#/appointment/12345',
            start  : '2016-07-07T16:30:00',
            end  : '2016-07-07T17:30:00',
            procedure : 'RCT'
        },
        {
            id     : 12345,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Naresh : RCT',
            allDay : false, // will make the time show
            url    :  '#/appointment/12345',
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
            header: {
                left: 'prev,next, today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
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