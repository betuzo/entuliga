define([
    'backbone'
], function(Backbone){

    var SanitizeModel = Backbone.Model.extend({

        defaults: {
        },

        initialize: function() {
          console.log("initialize global for inputs");
          _.each(this.attributes, function (val, key) {
            this.set(key, this.sanitize(val));
          }, this);
        },
        sanitize: function (str) {
          return _.escape(str);
        },
    });
	return SanitizeModel;
});
