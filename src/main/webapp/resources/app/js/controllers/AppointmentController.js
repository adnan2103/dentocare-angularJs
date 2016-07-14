'use strict';

/**
 * AppointmentController
 * @constructor
 */
var AppointmentController = function($scope, $http) {

    $scope.appointment = {
            id     : 12345,
            patientId : 767676,
            doctorId : 765757,
            title  : 'Avengers : RCT',
            allDay : false, // will make the time show
            url    :  '#/appointment/12345',
            start  : '2016-07-07T11:30:00',
            end  : '2016-07-07T12:30:00',
            procedure : 'RCT'
        };
};