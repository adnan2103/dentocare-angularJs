App.controller('TreatmentController',['$scope', '$http', '$routeParams' , function($scope, $http, $routeParams) {

    $scope.status = [
        {'status': 'In-Progress'},
        {'status': 'Closed'}
    ];

    $scope.fetchTreatment = function() {
        $http.get('patient/' + $routeParams.id).success(function(patient){
            $scope.patient = patient;
        });

        $http.get('patient/' + $routeParams.id + '/treatments').success(function(treatments){
            $scope.treatments = treatments;
            $scope.disableAddTreatment = false;

            if(!treatments[0]) {
                $scope.treatments[0] = treatment;
                $scope.disableAddTreatment = true;
             } else {
                angular.forEach($scope.treatments, function(treatments) {
                    $scope.updateTotalCost(treatments.id);
                    $scope.updateTotalPayment(treatments.id);
                });
            }


        });
    };


    var treatment = {
            "id":null,
            "chiefComplaintDescription":"",
            "notes":"",
            "status":"In-Progress",
            "totalCost":0,
            "totalPayment":0,
            "pendingPayment":0,
            "payment":[
                {
                    "id":null,
                    "paymentDate":new Date(),
                    "paymentAmount":0,
                    "treatmentDone":""
                }
            ],
            "patientOralExamination":[
                {
                    "id":null,
                    "description":"",
                    "cost":0
                }
            ]
        };

    $scope.updateTotalCost = function(treatmentId) {
        var cost = 0;
        angular.forEach($scope.findById($scope.treatments,treatmentId).patientOralExamination, function(patientOralExamination) {
            cost += patientOralExamination.cost;
        });
        $scope.findById($scope.treatments,treatmentId).totalCost = cost;
        $scope.findById($scope.treatments,treatmentId).pendingPayment = (cost - $scope.findById($scope.treatments,treatmentId).totalPayment);
    }

    $scope.updateTotalPayment = function(treatmentId) {
        var totalPayment = 0;
        angular.forEach($scope.findById($scope.treatments,treatmentId).payment, function(payment) {
            totalPayment += payment.paymentAmount;
        });
        $scope.findById($scope.treatments,treatmentId).totalPayment = totalPayment;
        $scope.findById($scope.treatments,treatmentId).pendingPayment = ($scope.findById($scope.treatments,treatmentId).totalCost - totalPayment);
    }


    $scope.saveTreatment = function(treatments) {
        $scope.resetError();

        $http.put('patient/' + $routeParams.id + '/treatments', treatments).success(function(treatments) {
            $scope.treatments = treatments;
            angular.forEach($scope.treatments, function(treatments) {
                $scope.updateTotalCost(treatments.id);
                $scope.updateTotalPayment(treatments.id);
            });
            $scope.message = 'Treatment Updated.';
            alert('Treatment Added/Updated Successfully.');
        }).error(function(error) {
            alert(error);
            $scope.setError('Could not update the treatment.');
        });
        $scope.disableAddTreatment = false;


    };

    $scope.addPayment = function(treatmentId) {
        var newPayment =
            {
                "id":null,
                "paymentDate": new Date(),
                "paymentAmount":0,
                "treatmentDone":""
            };
        $scope.findById($scope.treatments,treatmentId).payment.push(newPayment);
    };

    $scope.addPatientOralExamination = function(treatmentId) {
        var newPatientOralExamination =
        {
            "id":null,
            "description" : "",
            "cost" : 0
        };

        $scope.findById($scope.treatments,treatmentId).patientOralExamination.push(newPatientOralExamination);
    };

     $scope.findById = function(source, id) {
        for (var i = 0; i < source.length; i++) {
            if (source[i].id === id) {
                return source[i];
            }
        }
        throw "Couldn't find object with id: " + id;
    }

    $scope.addNewTreatment = function(size) {
        $scope.disableAddTreatment = true;
        $scope.treatments[size] = treatment;
    }

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchTreatment();

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
}]);