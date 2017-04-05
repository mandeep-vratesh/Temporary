<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript">

    //to set focus
    $(function() {
	  $("#input").focus();
	});

    //get live results
    	function getStatus(value){
			$.post( "getResults.php",{partialState:value}, function( data ) {
				  $( "#results" ).html( data );
			});

		}
    </script>

    <style type="text/css">
    	#input{
    		width: 100%;
    		height: 25px;
    		font-size: 20px;
    	}
    	#results{
    		margin-top: 15px;
    	}
    </style>
</head>

<body>
<div>Enter product name to search:</div>
<div id="search_box" style="opacity:.7">
    <input id="input" required='' type='text' onKeyUp="getStatus(this.value)">
	<label alt='Search all recipes/ingredients' placeholder='Search all recipes/ingredients'></label>
</div>
<div id="results"></div>
</body>
</html>