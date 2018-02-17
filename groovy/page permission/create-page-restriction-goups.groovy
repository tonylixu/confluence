// CNF version 6.5.0
// Script will add defined groups to page restriction on page create.

import com.atlassian.confluence.event.events.content.page.PageEvent
import com.atlassian.confluence.core.ContentPermissionManager
import com.atlassian.confluence.core.DefaultContentPermissionManager
import com.atlassian.confluence.security.ContentPermission
import com.atlassian.sal.api.component.ComponentLocator
import com.atlassian.confluence.user.UserAccessor
import com.atlassian.user.GroupManager
import org.apache.log4j.Logger
import org.apache.log4j.Level

// Set logger
def log = Logger.getLogger("com.onresolve.jira.groovy")
log.setLevel(Level.INFO)

// Define event
def event = event as PageEvent
log.debug("event defined: " + event)

// Retrieve user name and create a user object
def groupManager = ComponentLocator.getComponent(GroupManager)
def userAccessor = ComponentLocator.getComponent(UserAccessor)
def creator = event.getPage().getCreator()
log.info("Page creator is " + creator['name'])
def username = creator['name'].toString()
def user = userAccessor.getUserByName(username)

// Retrieve groups of the user
def groups = userAccessor.getGroupNames(user)
log.info("list of groups are " + groups)