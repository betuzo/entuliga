define([
    'backbone'
], function(Backbone){

    var BaseModel = Backbone.Model.extend({

        defaults: {
        },

        initialize: function() {
          console.log("initialize global for inputs xss");
          _.each(this.attributes, function (val, key) {
            this.set(key, _.escape(val));
          }, this);
        }


    });
	return BaseModel;
});
