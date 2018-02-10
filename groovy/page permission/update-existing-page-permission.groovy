// CNF version 6.5.0
import com.atlassian.confluence.pages.Page
import com.atlassian.confluence.pages.PageManager
import com.atlassian.confluence.spaces.Space
import com.atlassian.confluence.spaces.SpaceManager
import com.atlassian.sal.api.component.ComponentLocator
import com.atlassian.confluence.core.ContentPermissionManager
import com.atlassian.confluence.core.DefaultContentPermissionManager
import com.atlassian.confluence.security.ContentPermission
import com.atlassian.confluence.security.ContentPermissionSet
import groovy.xml.MarkupBuilder

import com.atlassian.sal.api.component.ComponentLocator

import org.apache.log4j.Logger
import org.apache.log4j.Level

def log = Logger.getLogger("com.onresolve.jira.groovy")
log.setLevel(Level.WARN)


def spaceManager = ComponentLocator.getComponent(SpaceManager)
def pageManager = ComponentLocator.getComponent(PageManager)

// Set the space
def space = spaceManager.getSpace('EETP')
log.info("spaces: " + space)

// Retrieve all the pages in a given space
def pages = pageManager.getPages(space, true).findAll{it instanceof Page}
for (page in pages) {

    log.info("process page: " + page.title)
    // Check if the page has view permission
    ContentPermissionSet viewPermit = page
                    .getContentPermissionSet(ContentPermission.VIEW_PERMISSION);
    log.info("view permission is: " + viewPermit)
    //page.removeContentPermissionSet(viewPerms)
    
    // Check if the page has edit permission
    ContentPermissionSet editPermit = page
                    .getContentPermissionSet(ContentPermission.EDIT_PERMISSION);
    log.info("edit permission is: " + editPermit)
    //page.removeContentPermissionSet(editPerms)
    
    // Set the view permission
    if (viewPermit == null) {
    	def permissionManager = ComponentLocator.getComponent(ContentPermissionManager)
    	def editRestriction = ContentPermission.createGroupPermission(ContentPermission.VIEW_PERMISSION,"group-name")
    	permissionManager.addContentPermission(editRestriction, page)
    }
}
