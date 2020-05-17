//
//  ViewController.swift
//  KMP Weather Demo
//
//  Created by Igor on 01.05.2020.
//  Copyright © 2020 OwlsDevelopers. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController {

    private var viewModel: HomeViewModel!
    
    // View
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var button: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = CommonKt.createApplicationScreenMessage()
        button.addTarget(self, action: #selector(didButtonClick), for: .touchUpInside)
        
        viewModel = HomeViewModel()
        
        viewModel.labelLiveData.addObserver { (value) in
                self.label.text = value as? String
        }
    }
    
    @objc func didButtonClick(_ sender: UIButton) {
        viewModel.buttonPressCommand()
    }
    

    deinit {
        viewModel.onCleared()
    }

}

