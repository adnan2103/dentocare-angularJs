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
        var uploadUrl = 'patient/' + patient.id + '/image';
        fileUpload.uploadFileToUrl($scope.image, uploadUrl);
        $scope.patient.imagePath = uploadUrl;
        $scope.message = 'Photo Uploaded Successfully.';
    };

    $scope.fetchPatient();

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
};