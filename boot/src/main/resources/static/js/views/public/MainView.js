define([
	'jquery',
	'jqueryUI',
	'bootstrap',
	'jqueryValidate',
	'entuliga',
	'core/BaseView',
	'collections/TorneosCollection',
	'text!templates/public/tplMain.html'
], function($, jqueryUI, bootstrap, jqueryValidate, entuliga, BaseView, TorneosCollection, tplMain){

	var MainView = BaseView.extend({
        template: _.template(tplMain),

        events: {
        },

        initialize: function() {
            this.torneos = new TorneosCollection();
            this.listenTo(this.torneos, 'add', this.agregarTorneo);
            this.listenTo(this.torneos, 'sync', this.syncTorneos);

            this.torneos.fetch();
            this.torneosMap = [];
        },

        render: function() {
            this.$el.html(this.template());
            $('nav').hide();
            return this;
        },

        agregarTorneo: function(modelo) {
            this.torneosMap.push({label: modelo.get('descripcion'), value: modelo.get('id')});
        },

        syncTorneos: function() {
            this.setUp();
        },

        setUp: function() {
            that = this;
            $( "#select-torneo" ).autocomplete({
                minLength: 0,
                source: that.torneosMap,
                focus: function( event, ui ) {
                    $( "#select-torneo" ).val( ui.item.label );
                    return false;
                },
                select: function( event, ui ) {
                    $( "#select-torneo" ).val( ui.item.label );
                    $( "#torneo-id" ).val( ui.item.value );

                    return false;
                }
            })
            .autocomplete( "instance" )._renderItem = function( ul, item ) {
                return $( "<li>" )
                    .append( "<a>" + item.label + "</a>" )
                    .appendTo( ul );
            };
        }
	});

	return MainView;

});