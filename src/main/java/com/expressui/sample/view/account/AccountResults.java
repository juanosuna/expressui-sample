/*
 * Copyright (c) 2011 Brown Bag Consulting.
 * This file is part of the ExpressUI project.
 * Author: Juan Osuna
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License Version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
 * Brown Bag Consulting, Brown Bag Consulting DISCLAIMS THE WARRANTY OF
 * NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the ExpressUI software without
 * disclosing the source code of your own applications. These activities
 * include: offering paid services to customers as an ASP, providing
 * services from a web application, shipping ExpressUI with a closed
 * source product.
 *
 * For more information, please contact Brown Bag Consulting at this
 * address: juan@brownbagconsulting.com.
 */

package com.expressui.sample.view.account;

import com.expressui.core.view.CrudResults;
import com.expressui.core.view.ResultsTable;
import com.expressui.core.view.field.DisplayFields;
import com.expressui.core.view.field.format.JDKFormatPropertyFormatter;
import com.expressui.sample.dao.AccountDao;
import com.expressui.sample.entity.Account;
import com.vaadin.terminal.Sizeable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.Locale;

@Component
@Scope("prototype")
@SuppressWarnings({"serial"})
public class AccountResults extends CrudResults<Account> {

    @Resource
    private AccountDao accountDao;

    @Resource
    private AccountQuery accountQuery;

    @Resource
    private AccountForm accountForm;

    @Override
    public AccountDao getEntityDao() {
        return accountDao;
    }

    @Override
    public AccountQuery getEntityQuery() {
        return accountQuery;
    }

    @Override
    public AccountForm getEntityForm() {
        return accountForm;
    }

    @Override
    public void configureFields(DisplayFields displayFields) {
        displayFields.setPropertyIds(new String[]{
                "name",
                "billingAddress.state.code",
                "billingAddress.country",
                "mainPhone",
                "numberOfEmployees",
                "annualRevenueInUSD",
                "lastModified",
                "modifiedBy"
        });

        displayFields.setLabel("billingAddress.state.code", "State");
        displayFields.setLabel("mainPhone", "Phone");
        displayFields.setLabel("numberOfEmployees", "# of Employees");
        displayFields.setLabel("annualRevenueInUSD", "Annual Revenue");
        displayFields.setSortable("mainPhone", false);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(0);
        JDKFormatPropertyFormatter formatter = new JDKFormatPropertyFormatter(numberFormat);
        displayFields.setPropertyFormatter("annualRevenueInUSD", formatter);
    }

    @Override
    public void configureTable(ResultsTable resultsTable) {
        resultsTable.setWidth(70, Sizeable.UNITS_EM);
    }
}
