'use strict';

/**
 * AppointmentsController
 * @constructor
 */
var AppointmentsController = function ($scope, $http, $routeParams, appointmentService, uiCalendarConfig) {

    $scope.eventSources = [];

    $scope.fetchAppointments = function () {

        $http.get('appointments').success(function (appointments) {
            angular.forEach(appointments, function (appointment) {
                if ($routeParams.id == appointment.patientId) {
                    appointment['backgroundColor'] = 'red',
                        appointment['color'] = 'red'
                }
            });

            $scope.appointments = appointments;
            $scope.eventSources.push(appointments);

        })
    }
    $scope.fetchAppointments();

    $scope.calendarConfig = {
        height: 450,
        timeFormat: 'h(:mm)t',
        defaultView: 'agendaWeek',
        businessHours: {
            start: '09:00',
            end: '21:00',
            dow: [1, 2, 3, 4, 5, 6]
        },
        selectable: $routeParams.id === 'all' ? false : true,
        selectHelper: $routeParams.id === 'all' ? false : true,
        editable: true,
        selectOverlap: false,

        eventOverlap: false,
        header: {
            left: 'prev,next, today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        eventDrop: function (event, delta, revertFunc) {

            if (!confirm("Are you sure to set new appointment from " + event.start.format('LLLL') + " to " + event.end.format('LLLL'))) {
                revertFunc();
                return;
            }
            var appointment = $scope.findById($scope.appointments, event.id);
            appointment['start'] = event.start.format();
            appointment['end'] = event.end.format();

            $scope.saveAppointment(appointment);
        },
        eventResize: function (event, delta, revertFunc) {

            if (!confirm("Are you sure to set appointment from " + event.start.format('LLLL') + " to " + event.end.format('LLLL'))) {
                revertFunc();
                return;
            }
            var appointment = $scope.findById($scope.appointments, event.id);
            appointment['start'] = event.start.format();
            appointment['end'] = event.end.format();

            $scope.saveAppointment(appointment);
        },
        select: function (start, end, jsEvent, view) {

            if (!confirm("Are you sure to set new appointment from " + start.format('LLLL') + " to " + end.format('LLLL'))) {
                //uiCalendarConfig.calendars[calendar].fullCalendar('unselect');
                return;
            }
            var events = new Array();
            var event = new Object();
            event.title = 'RCT';
            event.start = start;
            event.end = end;
            event.allDay = false;
            events.push(event);
            //uiCalendarConfig.calendars[calendar].fullCalendar('addEventSource', events);

            var appointment = {
                "id": null,
                "patientId": $routeParams.id,
                "doctorId": null,
                "start": start,
                "end": end,
                "plannedTreatment": "RCT"
            }
            $scope.saveAppointment(appointment);

        },
        events: $scope.appointments
    };



    $scope.saveAppointment = function (appointment) {
        var promise = appointmentService.save(appointment);

        promise.then(function (response) {
            alert('Appointment Set Successfully.');
        }, function (error) {
            alert(error);
        }, function (update) {
            alert(update);
        });
    };

    $scope.findById = function (source, id) {
        for (var i = 0; i < source.length; i++) {
            if (source[i].id === id) {
                return source[i];
            }
        }
        throw "Couldn't find object with id: " + id;
    }

};