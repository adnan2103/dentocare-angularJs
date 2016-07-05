'use strict';

/**
 * AppointmentController
 * @constructor
 */
var AppointmentController = function($scope, $document) {

    var $jq = jQuery.noConflict();

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
            dayClick: function() {
                alert('a day has been clicked!');
            }
        })

    });
};