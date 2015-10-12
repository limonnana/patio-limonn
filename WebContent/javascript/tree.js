if (!XMLHttpRequest && window.ActiveXObject)
{
    function XMLHttpRequest()
    {
        return new ActiveXObject('Msxml2.XMLHTTP') || new ActiveXObject('Microsoft.XMLHTTP');
    }
}

function addEventListener(target, eventName, functionReference, useCapture)
{
    if (typeof(useCapture) == 'undefined')
		useCapture = false;

    if (target.addEventListener)
        target.addEventListener(eventName, functionReference, useCapture);
    else if (target.attachEvent)
        target.attachEvent('on' + eventName, functionReference);
}


function text(s)
{
	return document.createTextNode(s);
}

function element(t)
{
	return document.createElement(t);
}

function toggleChildren(link, id)
{
	var event = null;
	
	if ((link.target) || (link.srcElement))
	{
		event = link;
		link = link.target || link.srcElement;
		id = link.simpleObjectId;
	}

	var parent = link.parentNode;
	var ul, i;
	
	for (i = 0; i < parent.childNodes.length; i++)
	{
		if (parent.childNodes[i].nodeName == 'UL')
		{
			ul = parent.childNodes[i];
			break;
		}
	}

	if (ul)
	{
		if (ul.style.display == 'none')
		{
			ul.style.display = 'block';
			setText(link, '-');
		}
		else
		{
			ul.style.display = 'none';
			setText(link, '+');
		}
			
		return preventDefault(event);
	}
	
	var request = new XMLHttpRequest();
	
	request.open('GET', contextPath + '/SimpleExample.action?getChildren&simpleObject=' + id, false);
	request.send('');
	
	var list = eval(request.responseText);
	
	ul = element('ul');
	
	var simpleObject, li, a;

	for (i = 0; i < list.length; i++)
	{
		simpleObject = list[i];
		li = element('li');
		
		if (simpleObject.children.length > 0)
		{
			li.appendChild(text('['));
			a = element('a');
			a.href = '#';
			a.simpleObjectId = simpleObject.id;
			addEventListener(a, 'click', toggleChildren);
			a.appendChild(text('+'));
			li.appendChild(a);
			li.appendChild(text('] '));
		}
		
		a = element('a');
		a.href = contextPath + '/SimpleExample.action?simpleObject=' + simpleObject.id;
		a.appendChild(text(simpleObject.name));
		li.appendChild(a);
		
		ul.appendChild(li);
	}
	
	parent.appendChild(ul);
	setText(link, '-');
	
	return preventDefault(event);
}

function preventDefault(event)
{
	if (event)
	{
		if (event.preventDefault)
			event.preventDefault();
		else
			event.returnValue = false;
	}

	return false;	
}

function setText(link, string)
{
	while (link.childNodes.length > 0)
		link.removeChild(link.childNodes[0]);
		
	link.appendChild(text(string));
}
