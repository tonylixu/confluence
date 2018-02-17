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