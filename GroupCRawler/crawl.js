FB.api(
  '/me',
  'GET',
  {"fields":"id,name,groups{feed{likes{name}}}"},
  function(response) {
      console.log("test");
  }
);