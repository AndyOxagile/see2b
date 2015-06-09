/*
    JS library reference:
    http://developers.bistri.com/webrtc-sdk/js-library-reference
*/

// when Bistri API client is ready, function
// "onBistriConferenceReady" is invoked
onBistriConferenceReady = function () {
    
    var localStream;
    
    // initialize API client with application keys
    // if you don't have your own, you can get them at:
    // https://api.developers.bistri.com/login
    bc.init( {
        appId: "ea108615",
        appKey: "d9d3ff861e9c4bc2a53af36ed8f5435c"
    } );

    // test if the browser is WebRTC compatible
    if ( !bc.isCompatible() ) {
        // if the browser is not compatible, display an alert
        alert( "your browser is not WebRTC compatible !" );
        // then stop the script execution
        return;
    }

    /* Set events handler */

    // when local user is connected to the server
    bc.signaling.bind( "onConnected", function () {
        // ask the user to access to his webcam and set the resolution to 320x240
        bc.startStream( "780x600", function( stream ){
            // set "localStream" variable with the local stream
            localStream = stream;
             // insert the local webcam stream into the page body, mirror option invert the display
            bc.attachStream( stream, document.body, { mirror: true } );
            // join a conference room called "conference_demo" and limit conference to 4 participants
            bc.joinRoom( "conference_demo", 4 );
        });
    });

    // when the user has joined a room
    bc.signaling.bind( "onJoinedRoom", function ( result ) {
        // set room members array in a var "roomMembers"
        var roomMembers = result.members;
         // then, for every single members already present in the room ...
        for ( var i=0, max=roomMembers.length; i<max; i++ ) {
            // ... request a call
            bc.call( roomMembers[i].id, "conference_demo", { stream: localStream } );
        }
    });

    // when a new remote stream is received
    bc.streams.bind( "onStreamAdded", function ( remoteStream ) {
        // insert the new remote stream into the page body
        bc.attachStream( remoteStream, document.body );
    });
    
    // when a stream has been stopped
    bc.streams.bind( "onStreamClosed", function ( stream ) {
        // remove the stream from the page
        bc.detachStream( stream );
    } );

    // open a new session on the server
    bc.connect();

}