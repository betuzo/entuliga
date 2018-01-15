define([
  'jquery',
  'backbone',
  'marionette',
], function($, Backbone, Mn) {

  var Transition = Mn.Object.extend({
    duration: 700,

    apply: function(el, type, callback) {
      var transitionClass = 'animated ' + type;
      el.addClass(transitionClass);
      setTimeout(callback, this.duration);
    },

    initialize: function() {},


  });

  return Transition;
});
