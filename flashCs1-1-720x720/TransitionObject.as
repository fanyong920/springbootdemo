package
{
	import fl.transitions.*;
	import fl.transitions.easing.*;

	public class TransitionObject
	{
		private var obj:Object = new Object();
		private var myDuration:Number = 0.5;
		private var tweenArray:Array = ["Blinds","Fade","Iris","PixelDissolve","Fly","Rotate","Squeeze","Wipe","Zoom","Photo"];
		public function TransitionObject(templet:int,index:int)
		{
			var tweenStr:String = tweenArray[templet-1];
			switch(tweenStr){
				case "Blinds":
					if(index%2==0){
						obj = {type:Blinds, direction:Transition.IN, duration:myDuration, easing:None.easeNone, numStrips:20, dimension:0};
					}else{
						obj = {type:Blinds, direction:Transition.IN, duration:myDuration, easing:None.easeNone, numStrips:20, dimension:1};
					}
					break;
				case "Fade":
					obj = {type:Fade, direction:Transition.IN, duration:myDuration, easing:None.easeNone};
					break;
				case "Iris":
					if(index%2==0){
						obj = {type:Iris, direction:Transition.IN, duration:myDuration, easing: None.easeNone, startPoint:5, shape:Iris.CIRCLE};
					}else{
						obj = {type:Iris, direction:Transition.IN, duration:myDuration, easing: None.easeNone, startPoint:5, shape:Iris.SQUARE};
					}
					break;
				case "PixelDissolve":
					obj = {type:PixelDissolve,  direction:Transition.IN, duration:myDuration, easing:None.easeNone, xSections:20, ySections:20};
					break;
				case "Fly":
					switch(index){
						case 1:
							obj = {type:Fly, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:4};
							break;
						case 2:
							obj = {type:Fly, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:2};
							break;
						case 3:
							obj = {type:Fly, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:6};
							break;
						case 4:
						default:
							obj = {type:Fly, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:8};
							break;
					}
					break;
				case "Photo":
					obj = {type:Photo,  direction:Transition.IN, duration:myDuration, easing:None.easeNone};
					break;
				case "Rotate":
					if(index%2==0){
						obj = {type:Rotate,  direction:Transition.IN, duration:myDuration, easing:None.easeNone, ccw:false, degrees:270};
					}else{
						obj = {type:Rotate,  direction:Transition.IN, duration:myDuration, easing:None.easeNone, ccw:true, degrees:270};
					}
					break;
				case "Squeeze":
					if(index%2==0){
						obj = {type:Squeeze,  direction:Transition.IN, duration:myDuration, easing:None.easeNone, dimension:1};
					}else{
						obj = {type:Squeeze,  direction:Transition.IN, duration:myDuration, easing:None.easeNone, dimension:0};
					}
					break;
				case "Wipe":
					switch(index){
						case 1:
							obj = {type:Wipe, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:1};
							break;
						case 2:
							obj = {type:Wipe, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:3};
							break;
						case 3:
							obj = {type:Wipe, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:9};
							break;
						case 4:
						default:
							obj = {type:Wipe, direction:Transition.IN, duration:myDuration, easing:None.easeNone, startPoint:7};
							break;
					}
					break;
				case "Zoom":
				default:
					obj = {type:Zoom, direction:Transition.IN, duration:myDuration, easing:None.easeNone};
					break;
			}
		}
		
		public function get object():Object{
			return obj;
		}
	}
}