#!/usr/bin/python

import appindicator
import pynotify
import gtk

#a = appindicator.Indicator('tubecheck', '/home/paul/scripts/neo.png', appindicator.CATEGORY_APPLICATION_STATUS)
#a = appindicator.Indicator('tubecheck', 'http://a0.twimg.com/profile_images/283061581/neo_reasonably_small.png', appindicator.CATEGORY_APPLICATION_STATUS)
a = appindicator.Indicator('tubecheck', '/usr/share/pixmaps/apple-green.png', appindicator.CATEGORY_APPLICATION_STATUS)
a.set_status( appindicator.STATUS_ACTIVE )
m = gtk.Menu()
ci = gtk.MenuItem( 'Check' )
qi = gtk.MenuItem( 'Quit' )

m.append(ci)
m.append(qi)

a.set_menu(m)
ci.show()
qi.show()

def checkStatus(item):
	import urllib2
	htmltext = urllib2.urlopen('http://youtube.com/wichitsombat').readlines()
	neededline = []
	for line in htmltext:
		if line.strip().find('stat-value') > -1:
			neededline.append(line)	

	n = neededline[0]
	subs = n[n.find('>')+1:n.rfind('<')]
	n = neededline[1]
	views = n[n.find('>')+1:n.rfind('<')]
	
	# show the notification message
	pynotify.init('tubecheck')
	n = pynotify.Notification('<b>Paulgramming Channel</b>',
		'subscribers: %s   views: %s'%(subs, views),
		'notification-message-im')
	n.show()

ci.connect('activate', checkStatus)

def quit(item):
	gtk.main_quit()

qi.connect('activate', quit)

gtk.main()


