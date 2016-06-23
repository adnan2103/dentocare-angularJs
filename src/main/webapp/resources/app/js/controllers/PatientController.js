'use strict';

/**
 * PatientController
 * @constructor
 */
var PatientController = function($scope, $http, $routeParams, fileUpload, patientService) {

    $scope.fetchPatient = function() {
        if ($routeParams.id !== 'new') {
            $http.get('patient/' + $routeParams.id).success(function(patient){
                $scope.patient = patient;
            });
        } else {
            $scope.patient = {};
        }
        $scope.random = 232112;
    };

    $scope.gender = [
        {'gender': 'Male'},
        {'gender': 'Female'}
    ];

    $scope.savePatient = function(patient) {
        $scope.patient = patientService.update({}, patient);
    };

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.saveImage = function(patient){
        $scope.random = $scope.random + 1;
        var uploadUrl = 'patient/' + patient.id + '/image';
        var promise = fileUpload.uploadFileToUrl($scope.image, uploadUrl);

        promise.then(function(response) {
            $scope.patient.imagePath = uploadUrl + '?random=' + $scope.random;
            $scope.error = true;
            $scope.errorMessage = response;
        }, function(error) {
            $scope.setError(error);
        }, function(update) {
            $scope.setError('Got notification: ' + update);
        });
    };

    $scope.fetchPatient();

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
};