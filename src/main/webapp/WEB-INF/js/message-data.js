angular.module('message', []).controller('userCtrl', function ($scope, $http) {
  $http.get("https://leancloud.cn:443/1.1/login?username=anyshisong%40gmail.com&password=123456", {
      headers: {'X-LC-Id': 'lehRrcPFUSbhSBVqDxNSocVV-gzGzoHsz', 'X-LC-Key': 'tuEz3gPGmS54YsTWbRoqEg5c'}
    })
    .success(function (response) {
      $scope.greeting = response;
    });
});
