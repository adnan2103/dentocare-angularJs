'use strict';

/**
 * AppointmentsController
 * @constructor
 */
var AppointmentsController = function ($scope, $document, $http, $routeParams, appointmentService) {

    $scope.fetchAppointments = function () {

        $http.get('appointments').success(function (appointments) {

            $scope.patientId = $routeParams.id;
            angular.forEach(appointments, function (appointment) {
                if ($scope.patientId == appointment.patientId) {
                    appointment['backgroundColor'] = 'red',
                        appointment['color'] = 'red'
                }
            });

            $scope.appointments = appointments;


            var $jq = jQuery.noConflict();

            $document.ready(function () {
                // page is now ready, initialize the calendar...

                $jq('#calendar').fullCalendar({
                    // put your options and callbacks here

                    height: 450,
                    timeFormat: 'h(:mm)t',
                    defaultView: 'agendaWeek',
                    businessHours: {
                        start: '09:00',
                        end: '21:00',
                        dow: [1, 2, 3, 4, 5, 6]
                    },
                    selectable: $scope.patientId === 'all' ? false : true,
                    selectHelper: $scope.patientId === 'all' ? false : true,
                    editable: true,
                    selectOverlap: false,
                    eventOverlap: false,
                    header: {
                        left: 'prev,next, today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    select: function (start, end, jsEvent, view) {

                        if (!confirm("Are you sure to set new appointment from " + start.format('LLLL') + " to " + end.format('LLLL'))) {
                            $jq('#calendar').fullCalendar('unselect');
                            return;
                        }
                        var events = new Array();
                        event = new Object();
                        event.title = 'RCT';
                        event.start = start;
                        event.end = end;
                        event.allDay = false;
                        events.push(event);
                        $jq('#calendar').fullCalendar('addEventSource', events);

                        var appointment = {
                            "id": null,
                            "patientId": $scope.patientId,
                            "doctorId": null,
                            "start": start,
                            "end": end,
                            "plannedTreatment": "RCT"
                        }
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
                    events: $scope.appointments
                })

            });
        })
    }
    $scope.fetchAppointments();

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