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
            title  : 'Avengers : RCT',
            allDay : false, // will make the time show
            url    :  '#/appointment/12345',
            start  : '2016-07-07T11:30:00',
            end  : '2016-07-07T12:30:00',
            treatment : 'RCT'
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