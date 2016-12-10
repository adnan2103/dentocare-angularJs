angular.module('navigation', ['ngRoute', 'auth']).controller(
    'navigation',

    function($scope, $route, auth, $rootScope) {

        $scope.credentials = {};

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        $scope.authenticated = function() {
            return auth.authenticated;
        }

        $scope.login = function() {
            auth.authenticate($scope.credentials, function(user) {
                $rootScope.user = user;
                if (user.isAuthenticated) {
                    console.log("Login succeeded")
                    $scope.error = false;
                } else {
                    console.log("Login failed")
                    $scope.error = true;
                }
            })
        };

        $rootScope.isAuthenticated = function() {

            if ($rootScope.user === undefined) {
                return false;
            }
            if ($rootScope.user.isAuthenticated === undefined) {
                return false;
            }
            if (!$rootScope.user.isAuthenticated) {
                return false;
            }
            return true;
        };
        $rootScope.hasAccess = function(moduleId) {

            if (!$rootScope.isAuthenticated()) {
                return false;
            }
            if ($rootScope.user.modules[moduleId] === undefined) {
                return false;
            }
            return $rootScope.user.modules[moduleId];
        };

        $scope.logout = auth.clear;

    });
