 {
     baseUrl: "static/js",
     mainConfigFile: 'static/js/main.js',
     paths: {
        jquery: 'vendor/jquery/jquery',
        underscore: 'vendor/underscore/underscore-min',
        backbone: 'vendor/backbone/backbone-min',
        bootstrap: 'vendor/bootstrap/bootstrap',
        jquerycookie: 'vendor/jquery/cookie/jquery.cookie',
        jquerySerializeObject: 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        backboneValidation: 'vendor/backbone/backbone-validation/backbone-validation',
        text : 'vendor/requirejs-text/text',
        selecter: 'vendor/bootstrap/select/bootstrap-select.min',
        datetimepicker: 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        dateformat: 'vendor/date-format/date.format',
        bloodhound: 'vendor/typeahead/bloodhound',
        typeahead: 'vendor/typeahead/typeahead.jquery'
     },
     shim: {
         underscore: {
             exports: '_'
         },
         backbone: {
             deps: [
                 'underscore',
                 'jquery'
             ],
             exports: 'Backbone'
         },
         backboneValidation: {
             deps: [
                 'backbone'
             ]
         },
         bootstrap: {
             deps: [
                 'jquery'
             ]
         },
         selecter: {
             deps: [
                 'jquery'
             ]
         },
         jquerycookie:{
             deps: [
                 'jquery'
             ]        },
         jquerySerializeObject: {
             deps: [
                 'jquery'
             ]
         },
         datetimepicker: {
             deps: [
                 'jquery'
             ]
         },
         bloodhound: {
             deps: [
                 'jquery'
             ]
         },
         typeahead: {
             deps: [
                 'jquery'
             ]
         }
     },
      name: "main",
      out: "static/js/main-built.js"
 }