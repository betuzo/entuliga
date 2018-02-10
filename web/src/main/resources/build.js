 {
     baseUrl: "static/js",
     mainConfigFile: 'static/js/main.js',
     paths: {
        'jquery' : 'vendor/jquery/jquery',
        'underscore' : 'vendor/underscore/underscore-min',
        'backbone' : 'vendor/backbone/backbone-min',
        'backbone.radio' :'vendor/backbone/backbone-radio/backbone.radio.min',
        'marionette': 'vendor/backbone/backbone-marionette/backbone.marionette',
        'bootstrap': 'vendor/bootstrap/bootstrap',
        'jquerycookie': 'vendor/jquery/cookie/jquery.cookie',
        'jquerySerializeObject': 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        'backboneValidation': 'vendor/backbone/backbone-validation/backbone-validation-amd-min',
        'text' : 'vendor/requirejs-text/text',
        'selecter': 'vendor/bootstrap/select/bootstrap-select.min',
        'datetimepicker': 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        'dateformat': 'vendor/date-format/date.format',
        'bloodhound': 'vendor/typeahead/bloodhound',
        'typeahead': 'vendor/typeahead/typeahead.jquery',
        'fabric': 'vendor/fabric/fabric.min'
     },
     //enforceDefine: true,
     waitSeconds: 200,
     map: {
        '*': {
                'backbone.wreqr': 'backbone.radio'
            }
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
                  'jquery',
                  'underscore',
                  'backbone'
              ],
              exports: 'Backbone.Validation'
          },
         marionette:{
            deps : ['jquery', 'underscore', 'backbone'],
            exports: 'Mn'
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
         },
         fabric: {
             deps: [
                 'jquery'
             ]
         }
     },
      name: "main",
      out: "static/js/main-built.js"

 }